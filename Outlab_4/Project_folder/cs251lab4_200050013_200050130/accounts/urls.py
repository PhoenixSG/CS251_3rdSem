# accounts/urls.py
from django.urls import path

from .views import SignUpView
from . import views

urlpatterns = [
    path('accounts/signup/', SignUpView.as_view(), name='signup'),
    path('explore/', views.showthis, name='explore_wala'),
    path('explore/<name>', views.show_profile, name='explore_wala'),
    path('profile/', views.index, name='github_wala'),
]