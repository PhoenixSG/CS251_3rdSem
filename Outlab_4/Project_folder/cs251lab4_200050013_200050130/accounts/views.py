from django.shortcuts import render, redirect
from django.contrib import messages
from accounts.forms import SignUpForm
from django.contrib.auth.forms import UserCreationForm, authenticate
from django.contrib.auth.views import LoginView
from django.urls import reverse_lazy
from django.views import generic
from django.contrib.auth.models import User
from accounts.models import Profile
import datetime
from django.contrib.auth import get_user_model
# import json to load json data to python dictionary
import json
# urllib.request to make a request to api
import urllib.request
from datetime import datetime as dt

class SignUpView(generic.CreateView):
    form_class = SignUpForm
    success_url = reverse_lazy('login')
    template_name = 'registration/signup.html'


def signup_view(request):
    form = SignUpForm(request.POST)
    model_profile = Profile(NumFollowers=0, LastUpdated=datetime(2000, 1, 1, 1, 1, 1, 1), AllRepos="")
    model_profile.save()
    if form.is_valid():
        form.save()
        first_name = form.cleaned_data.get('first_name')
        last_name = form.cleaned_data.get('last_name')
        user = authenticate(username=username, password=password)
        login(request, user)
        return redirect('home')
    else:
        form = SignUpForm()
    return render(request, 'signup.html', {'form': form})

def set_attributes(name, followers=0, time=None, allrepos=""):
    print(name)
    user, _ = Profile.objects.get_or_create(user=name)
    print(user.NumFollowers)
    print(time)
    user.NumFollowers = followers or 0
    user.LastUpdated = time or datetime(2000, 1, 1, 1, 1, 1, 1)
    user.AllRepos = allrepos
        
    user.save()

def showthis(request):

    all_users= get_user_model().objects.all()
    context= {'all_users': all_users}
    temp=dict(context)
    print(temp)
    return render(request, 'explore.html', temp)

def show_profile(request, name):
    if request.user.username==name:
        print(request.user.username, name)
        return index(request)
    context= {'name': name}
    user=User.objects.get(username=name)
    context["last_name"]=user.last_name
    context["first_name"]=user.first_name
    temp=user.id
    user, _ = Profile.objects.get_or_create(user=temp)
    context["followers"]= user.NumFollowers
    context["time"]= user.LastUpdated.strftime("%Y-%m-%d %H:%M:%S")
    context["all_repos"]=str(user.AllRepos).split('|')

    print(user.AllRepos)
    print(context)
    return render(request, 'alt_profile.html', context)


def index(request):
    # return HttpResponse("You're looking at question ")

    if request.method == 'POST':
        print("HI") 
        try:
            username = request.POST['username'] 
            source = urllib.request.urlopen('https://api.github.com/users/' + username).read()
            print("Here!")

            source2 = urllib.request.urlopen('https://api.github.com/users/' + username + '/repos').read()
            
    
            # converting JSON data to a dictionary
            list_of_data = json.loads(source)
            list_of_data2 = json.loads(source2)
            arr=[]
            for i in list_of_data2:
                arr.append(str(i['name'])+" - " + str(i["stargazers_count"])+ " stars")
            # datetime object containing current date and time
            now = dt.now()
            
            print("now =", now)

            # dd/mm/YY H:M:S
            dt_string = now.strftime("%Y-%m-%d %H:%M:%S")
  
        # data={
        #     "followers": "vittvcy",
            
        # }
            # data={};
            # data for variable list_of_data
            n=len(list_of_data2)
            data = {
                "followers": str(list_of_data['followers']),
                "time": dt_string,
                "number": n,

            }
            print("YEFEIYF")
            all_repos={}
            string=""
            for i in range(n):
                all_repos[arr[i]] = 1
                string=string+str(arr[i])+"|"
            data["all_repos"]=all_repos
            # print(data["all_repos"])
            set_attributes(request.user, list_of_data['followers'], now, string[:-1])
            
            # print(data)
        except Exception as e:
            data={"error": "Oops!, Data could not be fetched due to some error, please try again or wait for some time"}
            print (str(e)+' Error!\n')
            user, _ = Profile.objects.get_or_create(user=request.user)
            data["followers"]=str(int(user.NumFollowers))
            data["time"]=str(user.LastUpdated.strftime("%Y-%m-%d %H:%M:%S"))
            data["all_repos"]=str(user.AllRepos).split('|')
            if int(user.NumFollowers)==-1:
                data["time"]="Not Updated Ever!"
                data["followers"]="#"
            print("Hello error", data["followers"], data["time"])
        return render(request, 'profile.html', data)
    else:
        data={}
        user, _ = Profile.objects.get_or_create(user=request.user)
        data["followers"]=str(int(user.NumFollowers))
        print(user.LastUpdated)
        data["time"]=str((user.LastUpdated+datetime.timedelta(hours=5, minutes=30)).strftime("%Y-%m-%d %H:%M:%S"))
        data["all_repos"]=str(user.AllRepos).split('|')

        print()
        print((user.AllRepos).split('|'))
        if int(user.NumFollowers)==-1:
            data["time"]="Not Updated Ever!"
            data["followers"]="#"
        print("Hello", data["followers"], data["time"])
        return render(request, 'profile.html', data)

        

