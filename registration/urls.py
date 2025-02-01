from django.urls import path
from .views import register_visitor, success, serve_badge, download_badge_pdf

urlpatterns = [
    path('', register_visitor, name='register_visitor'),
    path('success/<int:visitor_id>/', success, name='success'),
    path('badge/<int:visitor_id>/', serve_badge, name='generate_badge'),
    path('download_badge_pdf/<int:visitor_id>/', download_badge_pdf, name='download_badge_pdf'),
]
