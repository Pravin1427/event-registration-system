from django import forms
from .models import Visitor

class VisitorForm(forms.ModelForm):
    class Meta:
        model = Visitor  # Model used for form
        fields = ['full_name', 'email', 'phone', 'photo']  # Fields included in the form
