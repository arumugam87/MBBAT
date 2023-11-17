	Feature: Feature to MaxBlox BAT Automation

@MaxBloxlogin 
Scenario: Does the correct version information shows up in index.aspx and default.aspx pages

Given Get the MaxBlox released version from MFD server
Then open the MaxBlox application index login page 
Then Verify correct version information shows up in index.aspx
And open the MaxBlox application default login page
Then Verify correct version information shows up in default.aspx

@CompanyCreation
Scenario: Does the Login and company creation using Index.aspx page successful

Given open the MaxBlox Index login page
Then login to home page
Then Navigate to company page
Then Create a company
And  Edit the company and Company Server Details
Then Save the details
Then Verify the created company in DB

@CompanyVersionCheck
Scenario: Does the login and automatic upgrade for this newly created company successful

Given open the MaxBlox default login page
Then login to home page and choose the default
Then Verify the created company version in DB

@Registration
Scenario: Does the registration using MB_Registration page and subsequent activaton successful

Given open the MaxBlox Registration login page
Then Fill the Registration page fields
Then Click the Register button
Then Get the Url from the Backend
Then Activate the account using url

@AutomaticUpgradeforNewlyCreatedDB
Scenario: Does the login and automatic upgrade for this newly created company successful

Given Get the afrowid from ngcompany table
Then Verify the company version

@SingleDatabaseSingleschemaArchitecture
Scenario: Does the single database single schema architecture works successfully

Given Get the database name from ngcompanyserver table
Then Verify the Afrowid character

@SingleDatabaseMultipleschemaArchitecture
Scenario: Does the single database multiple schema architecture works correctly

Given open the MaxBlox Index page
Then login to Index Home Page
Then Navigate to company page
Then Create a secondcompany
And  Edit the secondcompany and secondCompany Server Details
Then Save all the details
Then Verify the number of database name ngcompanyserver

@CreationPageDesignerSimple
Scenario: Does the Simple page creation Page Designer and subsequent rendering of 
          the page works correctly

Given open the MaxBlox default page
When login to home page and choose the newly created company
Then Navigate to page Designer and create a page
Then Check the created page in menubar and List page

@UserCreationandLogin
Scenario: Does the user creation and login works correctly

Given open the MaxBlox usersetup page
Then Add user details and save the details
Then logout the user
Then Login into home page using newly created details

@CreationPageDesignerAdvanced
Scenario: Does the Advanced page creation Page Designer and subsequent rendering of the 
          page works correctly

Given open the MaxBlox default page
When login to home page and choose the newly created company
Then Navigate to advanced page Designer and create a page
Then Navigate to menu designer and add menu for the user
Then Navigate to Rolesetup page and assign role for the menu
Then Add record to the user 

@PageCreationSingleDatabaseMultipleSchema
Scenario: Check for same page creation in single database multiple schema

Given open Index page
Then login into mxblx home page
Then Navigate to firstcompany page
Then Create a firstcompany
And  Edit the Firstcompany and Firstcompany Server Details
Then Save the Firstcompany details
Then logout the Firstcompany
Then Login into Maxblox default page
Then Navigate to page designer and create a Firstcompany page
Then Check the created Firstcompany page in menubar and List page
When open Indexpage
Then login to mxblx Homepage
Then Navigate to secondcompanypage
Then Create secondcompany
And  Edit the secondcompany and secondcompany Server Details
Then Save the secondcompany details
Then logout the secondcompany
Then Login into Maxblox Defaultpage
Then Navigate to page designer and create a secondcompany page
Then Check the created secondcompany page in menubar and Listpage