from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.

from django.shortcuts import render
# import json to load json data to python dictionary
import json
# urllib.request to make a request to api
import urllib.request
from datetime import datetime


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
                arr.append(str(i['name'])+" - " + str(i["stargazers_count"]))
            # datetime object containing current date and time
            now = datetime.now()
            
            print("now =", now)

            # dd/mm/YY H:M:S
            dt_string = now.strftime("%d/%m/%Y %H:%M:%S")
  
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
            for i in range(n):
                data["repo"+str(i+1)] = arr[i]


            
            print(data)
        except KeyError:
            data={}
            print ('Error!\n')
        return render(request, 'profile.html', data)
    else:
        print("Hello")
        return render(request, 'profile.html', {})

        
