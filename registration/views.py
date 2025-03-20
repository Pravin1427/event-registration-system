from django.shortcuts import render, redirect
from django.urls import reverse
from django.http import HttpResponse, JsonResponse
from .forms import VisitorForm
from .models import Visitor
from PIL import Image, ImageDraw, ImageFont
import os
from django.conf import settings
from reportlab.pdfgen import canvas
from django.core.mail import EmailMessage
from django.http import JsonResponse


# Import logging utility
from event_registration.logging_service import log_info, log_error

# View to handle visitor registration
def register_visitor(request):
    try:
        if request.method == "POST":
            form = VisitorForm(request.POST, request.FILES)
            if form.is_valid():
                # Save the visitor
                visitor = form.save()

                # Generate the badge image
                generate_badge(visitor.id)

                # Send email with badge attachment
                send_badge_email(visitor)

                # Log successful visitor registration
                log_info("USER_ACTION", f"Visitor {visitor.full_name} registered successfully")

                # Redirect to success page with visitor ID
                return redirect(reverse('success', kwargs={'visitor_id': visitor.id}))
            else:
                # Log form validation failure
                log_error("FORM_ERROR", "Visitor form is invalid")
        else:
            form = VisitorForm()

        # Log when the registration form is accessed
        log_info("USER_ACTION", "User accessed the registration form")

        return render(request, 'registration/register.html', {'form': form})

    except Exception as e:
        # Log any errors that occur during the process
        log_error("SERVER_ERROR", str(e))
        return JsonResponse({"error": "Something went wrong"}, status=500)

# Success page View
def success(request, visitor_id):
    try:
        visitor = Visitor.objects.get(id=visitor_id)
        badge_url = reverse('generate_badge', kwargs={'visitor_id': visitor.id})
        pdf_url = reverse('download_badge_pdf', kwargs={'visitor_id': visitor.id})

        # Log success page access
        log_info("USER_ACTION", f"User accessed success page for visitor {visitor.full_name}")

        return render(request, 'registration/success.html', {'visitor': visitor, 'badge_url': badge_url, 'pdf_url': pdf_url})

    except Exception as e:
        # Log errors while accessing success page
        log_error("SERVER_ERROR", str(e))
        return JsonResponse({"error": "Something went wrong"}, status=500)

# Function to generate the visitor badge image
def generate_badge(visitor_id):
    try:
        visitor = Visitor.objects.get(id=visitor_id)

        # Create an image for the badge
        img = Image.new('RGB', (400, 200), color=(255, 255, 255))
        draw = ImageDraw.Draw(img)

        # Load a font for the text
        font = ImageFont.load_default()

        # Add visitor's name to the badge
        draw.text((50, 50), f"Visitor: {visitor.full_name}", fill="black", font=font)

        # Add the visitor's photo to the badge
        photo_path = os.path.join(settings.MEDIA_ROOT, str(visitor.photo))
        if os.path.exists(photo_path):
            visitor_photo = Image.open(photo_path).resize((100, 100))
            img.paste(visitor_photo, (250, 50))

        # Save the badge image
        badge_path = os.path.join(settings.MEDIA_ROOT, f"badges/{visitor.id}.png")
        os.makedirs(os.path.dirname(badge_path), exist_ok=True)
        img.save(badge_path)

        # Log badge generation
        log_info("USER_ACTION", f"Badge generated for visitor {visitor.full_name}")

        return badge_path
    except Exception as e:
        # Log errors during badge generation
        log_error("SERVER_ERROR", f"Error generating badge for visitor {visitor_id}: {str(e)}")
        raise

# Function to serve the badge image
def serve_badge(request, visitor_id):
    try:
        badge_path = os.path.join(settings.MEDIA_ROOT, f"badges/{visitor_id}.png")
        if os.path.exists(badge_path):
            with open(badge_path, "rb") as f:
                return HttpResponse(f.read(), content_type="image/png")
        return HttpResponse("Badge not found", status=404)
    except Exception as e:
        # Log errors during serving badge image
        log_error("SERVER_ERROR", f"Error serving badge for visitor {visitor_id}: {str(e)}")
        return JsonResponse({"error": "Something went wrong"}, status=500)

# Function to download the badge as a PDF
def download_badge_pdf(request, visitor_id):
    try:
        visitor = Visitor.objects.get(id=visitor_id)
        response = HttpResponse(content_type="application/pdf")
        response['Content-Disposition'] = f'attachment; filename="visitor_badge_{visitor_id}.pdf"'
        
        p = canvas.Canvas(response)
        p.setFont("Helvetica", 14)
        p.drawString(100, 750, f"Visitor Badge for: {visitor.full_name}")
        p.drawString(100, 730, f"Email: {visitor.email}")
        p.drawString(100, 710, f"Phone: {visitor.phone}")
        
        photo_path = os.path.join(settings.MEDIA_ROOT, str(visitor.photo))
        if os.path.exists(photo_path):
            p.drawImage(photo_path, 100, 600, width=100, height=100)

        p.showPage()
        p.save()

        # Log PDF download
        log_info("USER_ACTION", f"Visitor badge PDF downloaded for {visitor.full_name}")

        return response
    except Exception as e:
        # Log errors during PDF download
        log_error("SERVER_ERROR", f"Error generating PDF for visitor {visitor_id}: {str(e)}")
        return JsonResponse({"error": "Something went wrong"}, status=500)

# Send visitor badge via email
def send_badge_email(visitor):
    try:
        subject = "Your Event Visitor Badge"
        message = f"Hello {visitor.full_name},\n\nThank you for registering! Attached is your visitor badge."
        email = EmailMessage(subject, message, settings.DEFAULT_FROM_EMAIL, [visitor.email])

        # Attach the badge
        badge_path = os.path.join(settings.MEDIA_ROOT, f"badges/{visitor.id}.png")
        if os.path.exists(badge_path):
            email.attach_file(badge_path)

        email.send()

        # Log email sent
        log_info("USER_ACTION", f"Visitor badge email sent to {visitor.email}")

    except Exception as e:
        # Log errors during email sending
        log_error("SERVER_ERROR", f"Error sending badge email for {visitor.full_name}: {str(e)}")


def my_view(request):
    try:
        log_info("USER_ACTION", "User visited my_view")
        return JsonResponse({"message": "Success"})
    except Exception as e:
        log_error("SERVER_ERROR", str(e))
        return JsonResponse({"error": "Something went wrong"}, status=500)