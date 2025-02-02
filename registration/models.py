from django.db import models

# Django models for visitors
from django.db import models

class Visitor(models.Model):
    full_name = models.CharField(max_length=255) # Store visitor's full name
    email = models.EmailField(unique=True) # Store unique email
    phone = models.CharField(max_length=15) # Store phone number
    photo = models.ImageField(upload_to='visitor_photos/') # Store uploaded visitor photo

    def __str__(self):
        return self.full_name

