# Leave Request App Specifications

Το παρόν κείμενο περιγράφει ένα πλάνο για τις απαιτήσεις του έργου σύμφωνα με τις
απαιτήσεις του πελάτη, οπότε ενδέχεται να τροποποιηθεί.

### Requested Functionalities

1. Calendar
2. Personal Dashboard
3. Admin Dashboard
4. Sending email to inform about requests
5. Roles: Admin - User - Manager roles (access to everything)
6. Auto calculation of leave rights 
7. Multiple types of Leaves 
8. Track past requests
9. If possible, extract reports (CSV / Excel)
10. Web based application with User login
11. Deadline for managing leave requests. If the request isn't managed 
in due time it is automatically approved.

### Authorities By Role

Οι ελάχιστες απαιτήσεις που χρειάζονται για να σχεδιάσουμε την εφαρμογή
και να δημιουργήσουμε κάτι χειροπιαστό. email, reports, calendars μπορούν
να προστεθούν σε δεύτερη φάση.

Ο **User** θα μπορεί να:
- Αιτείται άδειες
- Βλέπει τις υπολειπόμενες μέρες αδειών του
- Ακυρώσει / επεξεργαστεί τα "ανοιχτά" αιτήματα αδειών του
- Κάνει μόνος του reset password (Προαιρετικό)

Ο **Manager** θα μπορεί να:
- Εγκρίνει / απορρίπτει αιτήματα των μελών της ομάδας του
- Βλέπει τις υπολειπόμενες μέρες αδειών της ομάδας του

Ο **Admin** θα μπορεί να:
- Δημιουργεί χρήστες
- Ενεργοποιεί/Απενεργοποιεί χρήστες
  - Όταν διαγράφει χρήστες, να διατηρείται το ιστορικό
- Στέλνει activation links & reset password
- Βγάζει reports με υπόλοιπα αδειών & χρήση αδειών (CSV File Minimum)
- Ομάδες
  - Δημιουργεί / Διαγράφει Ομάδες
  - Προσθέτει / Αφαιρεί μέλη / manager στις ομάδες
  - με ομάδες / managers και να δημιουργεί ομάδες
- Θέτει ρόλους στους Χρήστες
- Εγκρίνει / διαγράφει αιτήματα όλων των χρηστών
- Προσθέτει τύπους αδειών
- Αλλάζει το υπόλοιπο αδειών κάποιου χρήστη
- Να περνάει αναδρομικά άδειες που δεν είχανε δηλωθεί

### Additional Information

Ξεκινάμε by default με 4 τύπους αδειών:

Κανονική άδεια (ξεκινάμε με 20 ή 25 ημέρες ανά έτος)
Ασθένεια (δεν υπάρχει περιορισμός)
Τηλεργασία (δεν υπάρχει περιορισμός)
Άνευ αποδοχών (δεν υπάρχει περιορισμός)