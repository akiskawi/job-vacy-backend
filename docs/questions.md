# Θέματα προς συζήτηση

- Ένας χρήστης μπορεί να είναι manager σε πολλές ομάδες;
- Ένας χρήστης μπορεί να είναι μέλος σε πολλές ομάδες;
- Να κάνουμε τα id long?

- **db**.leave_request_available_days.taken Χρειάζεται να αποθηκεύεται η καλύτερα να υπολογίζεται?
- Θα υπάρχει **response message**? Τύπου "User Created", "User not found?"
    - Το μήνυμα θα στέλνεται από το backend η ανάλογα το http status θα δημιουργείται στο frontend
    - Αν στέλνεται από το backend θα είναι ένα string ή map<"message", content> ή κάποια άλλη μορφή;