# Generated by Django 3.2.6 on 2021-09-18 03:26

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='profile',
            name='AllRepos',
            field=models.CharField(default='|', max_length=100),
        ),
    ]
