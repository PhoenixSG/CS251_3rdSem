We'll explain and clarify a few things about our project, mostly pertaining to the front end aspects of it. As asked for in the document, we have made 5 webpages as asked for, namely the login page, sign-up page, profile page, explore page and the home page.

We have done both CSS and bootstrap styling of all the 5 pages, but since some bootstrap features which could've been done via CSS too, we need to clarify certain aspects so as to segregate the roles of each of those softwares:
1. CSS has been used to design the template webpages (the white and blue ones) as given in the problem statement : Thus, the blue buttons and clickable links are CSS artefacts. **Note that our buttons are "pushable", ie:- when a user clicks on it, it undergoes a motion which seems to us as pushing the button down.**
2. Bootstrap has been used to do a lot of formatting, inline, within the html document themselves : For example, the standard colors of **text-primary(bluish), text-warning (yellowish), text-info(greenish) and text-danger(reddish)** have been used extensively (as opposed to directly using say blue or yellow) to give a unique aesthetic and professional edge to our website. Also background color, making bold appropriate information, centering in buttons has also been done via bootstrap.
3. We have implemented atleast 3 different types of buttons (the big blue ones such as Explore, Logout which are pushable, the "User profile buttons" in the explore page which carry hyperlinks and the "Update Now" button in the profile page of a person).
4. In the explore page, **the buttons borrow from both our custom CSS code as well as bootstrap**. In particular, one of their features is that the buttons ensure **text-wrapping** so that long usernames don't overflow.
5. The design for a person's profile being opened (by somebody else : the alt-profile.html file) through the explore page or through the profile page (ie:- by the person herself : the alt-profile.html file) has been kept difference to give visual cues to the user regarding her position.


Some salient backend features :
1. Note that the profile of a person when opened by somebody else through the explore page doesn't contain the **Update Now** button. The owner of a profile should have the **sole** right to update it whenever she wishes.
2. When a profile logs into our database for the first time, her number of followers may be shown as a "#" mark owing to her logging in for the first time. On pressing the update now button though, all the relevant information is displayed immediately.
3. Our html files handle **even cases for which there doe not exist any github account, showing helpful error messages too. All this was made possible due to an elaborate handling of edgecaes in the html file via inline Conditional Statements.**
4. Our app shows the 30 repositories of the user as they were ordered in the GitHub API, and also handles their display properly.
5. Our **time display feature has been adjusted to IST**, as opposed to the default GMT time provided by python.
6. A single Profile model holds the info of the number of followers, last updated, and the repositories of the user.
