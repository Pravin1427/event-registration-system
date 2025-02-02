

from django.db import migrations, models


class Migration(migrations.Migration):
    # This is the initial migration for creating the Visitor model

    initial = True

    dependencies = [
    ] # No dependencies as this is the first migration

    operations = [
        migrations.CreateModel(
            name='Visitor',   # Creating a model named 'Visitor'
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('full_name', models.CharField(max_length=255)),
                ('email', models.EmailField(max_length=254, unique=True)),
                ('phone', models.CharField(max_length=15)),
                ('photo', models.ImageField(upload_to='visitor_photos/')),
            ],
        ),
    ]
