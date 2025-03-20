from django.urls import path
from .views import register_visitor, success, serve_badge, download_badge_pdf, my_view

urlpatterns = [
    path('', register_visitor, name='register_visitor'), # Homepage for registration
    path('success/<int:visitor_id>/', success, name='success'), # Success page with visitor badge
    path('badge/<int:visitor_id>/', serve_badge, name='generate_badge'), # Serve visitor badge image
    path('download_badge_pdf/<int:visitor_id>/', download_badge_pdf, name='download_badge_pdf'), # Download badge as PDF
    path("test-log/", my_view, name="test-log"),
]
