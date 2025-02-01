from django.db import models

# Create your models here.
from django.db import models

class Visitor(models.Model):
    full_name = models.CharField(max_length=255)
    email = models.EmailField(unique=True)
    phone = models.CharField(max_length=15)
    photo = models.ImageField(upload_to='visitor_photos/')

    def __str__(self):
        return self.full_name

