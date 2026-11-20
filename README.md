## Comp 342 Project

|Nicholas Chamoun |40264135|
|---|---|
|Prakhar Dixit| 40327655|
|Octavian Mihai| 40304833|

---
Phase 1 submission see: [Documentation/Iteration-1](Documentation/Iteration-1)

Phase 2 submission see: [Documentation/Iteration-2](Documentation/Iteration-2)

PDF document for the report and individual (higher quality) files for the diagrams

---
### Phase 2 – Proof of Concept (PoC)

The PoC implements the following functionalities: booking an appointment (walk-in or phone), searching for availability, making availability, importing from CSV and exporting to CSV.

**How to run** (Java 17 or newer), from inside the `Documentation/Iteration-2` folder:

    javac -encoding UTF-8 -d out src/*.java
    java -cp out Main

**Loading data:** the system starts fresh with nothing in memory. Use option 1 (Import from CSV) with these files, in this order:

1. `data/availabilities.csv` – professional availability
2. `data/booked_appointments.csv` – booked appointments (patients PT1–PT4)
3. `data/waiting_list.csv` – waiting list patients (PT5–PT9)
4. `data/services.csv` – services (GEN, CARD, DERM, ORTHO)
