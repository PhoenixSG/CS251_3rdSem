# Generated by Django 3.2.6 on 2021-09-18 03:29

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0002_profile_allrepos'),
    ]

    operations = [
        migrations.AlterField(
            model_name='profile',
            name='AllRepos',
            field=models.CharField(default='|', max_length=1000),
        ),
    ]
