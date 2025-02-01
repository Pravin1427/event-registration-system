from django.contrib import admin
from .models import Visitor

@admin.register(Visitor)
class VisitorAdmin(admin.ModelAdmin):
    list_display = ('full_name', 'email', 'phone', 'photo')
    search_fields = ('full_name', 'email')

admin.site.site_header = "Event Registration Admin"
admin.site.site_title = "Admin Panel"
admin.site.index_title = "Manage Visitors"
