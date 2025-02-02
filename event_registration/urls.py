from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    # Admin site URL
    path('admin/', admin.site.urls),
    # Include the URL configurations of the 'registration' app
    path('', include('registration.urls')),
] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
