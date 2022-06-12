# Onboarding App

## Objective
Write a small Android app that onboard a user if she isn’t “signed in”.
The app consists of (at least) seven screens, all super simple with absolutely zero
requirement of any UI what so ever.
This assignment is meant to test you skills at handling navigation
and architecture. Beautiful Java/Kolin code and a good architecture
with well-handled navigation is more important the the UI in this
assignment.
You are free use whatever architecture you like and you are free to use as many
dependencies as you like.

## Requirements
• Upon app start app need to check if user is “signed in”, if she is the Main
screen is showed with the users first name and last name. Otherwise user
should go through the onboarding flow.

• User must be able to go back to previous screens in onboarding, i.e. from
Personal Info, go back to Credentials and change the email.

## Implementation
The application follows an MVVM, clean architecture pattern and I have used Hilt for dependency injection. 

The architecture setup for this project has been built with the intention that the application could easily scale should additional features be included.
Should we want to add many more features a multi-module setup could be considered. 

I have utilised the Navigation Component library to handle navigation and animations. I also have included unit tests for the onboarding use cases.

## Bonus
Of the bonus items I have implemented the following

• Animated transitions between screens

• Tests of the code

• Viewing of telephone, email and name when login

• Have better UI than most ugly possible
