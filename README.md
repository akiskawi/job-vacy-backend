# Backend Api for job day off

### Functionalities

1. Calendar
2. Personal Dashboard
3. Admin Dashboard
4. When submitting request, sending an email
5. Receiving an email when request has been approved / rejected
6. Admin roles (access to everything)
7. User roles (access to his/her data)
8. Manager roles (access to his/her team)
9. Creation of Teams (by Admin)
10. Auto calculation of leave rights
11. Multiple types of Leaves
12. Examine possibility of extracting reports (CSV / Excel), if possible
13. Report / Track Record of past requests
14. Web based application with User login &amp; password

| Roles   | Permissions                                                                                                  |
|---------|--------------------------------------------------------------------------------------------------------------|
| Admin   | Register/remove users add "days off" days <br/>Create new types of "days off"<br/> Can be Manager &amp; User |
| Manager | Approved/rejected requests of their team<br/>See all past requests of their team                             |
| User    | See their past requests, submit request                                                                      |


##### Version 1

 - Type of leave "default"
 - Number of day per type
     -  Default = 20 