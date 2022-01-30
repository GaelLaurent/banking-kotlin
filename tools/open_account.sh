curl --verbose --location --request POST 'http://localhost:8080/account' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstname": "Bob",
    "lastname": "Mc Donald",
    "email": "bob.macdonald@plop.com",
    "phoneNumber": "05 03 03 03 03",
    "currency": "DOLLAR",
    "firstDeposit": 42
}'
