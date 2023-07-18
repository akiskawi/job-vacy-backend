# Σχεδιασμός και Αρχιτεκτονική

### Σχεδιασμός Πινάκων Βάσης Δεδομένων

**_user**
- id
- firstname - Για καλύτερη αναγνώριση του χρήστη
- lastname - // - // -
- email - Για σύνδεση και αντιστοίχιση με εταιρικό email
- password - // -
- roles - Οι ρόλοι του Χρήστη
- team_id - foreign key (team.id)
- enabled

**team**
- id
- manager_id - foreign key (users.id) To id του manager της ομάδας


**leave_request**

*Πίνακας για τα αιτήματα αδειών*

- id
- type - τύπος άδειας
- startDate
- endDate
- status - (values: PENDING, DENIED, APPROVED)
- user_id
- createdAt - simple Auditing για την απαίτηση να επεξεργάζονται τα αιτήματα σε συγκεκριμένο χρόνο.
- updatedAt

**leave_request_available_days**

*Αποθηκεύει τις διαθέσιμες μέρες για κάθε τύπο άδειας για κάθε χρήστη. Προέρχεται 
από την απαίτηση ότι ο admin μπορεί να προσθέσει ημέρες αδειών σε κάποιον χρήστη.*

- id
- taken 
- remaining
- type - τύπος άδειας
- user_id

**token**
*Αποθηκεύει το jwtToken του χρήστη*
- id
- token (unique)
- revoked (boolean)
- user_id

### To Consider

leave_request_available_days.taken Χρειάζεται να αποθηκεύεται η καλύτερα να υπολογίζεται?

### ER Diagram

![](/docs/ER_Diagram.svg)

![](\docs\ER_Diagram.svg)