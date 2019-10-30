# File Encryption
- created AES system to decrpt/encrpt files
- uses 16 bits (might change to 32)
- any higher bits takes too long to process
- has been implemented into bank 
- ***IMPORTANT*** You must exit thru the program, dont manually exit, pls

# Bank



***Update October 17, 2019***
- You can now withdraw/deposit money from your account
- User files will now be updated with new balance
- user can now withdraw/deposit multiple times
- user can now exit ATM
- cleaned up code

***Update October 18, 2019***
- atm now works with new file layout
- added method that exchanges usd -> cad ＆ cad -> usd
- deposits/withdraws will now be added to users account
- user can now view trasaction history
- moved some code around

***Update October 21, 2019***
- transaction history is now properly written to file
- user can now ask for Bank Balance Enquiry
- BankTeller is functional
- Can create and alter accounts
- Reads the text file into variables.

***Update October 22, 2019***
- user can now exchange currency

***Update October 23, 2019***
- ATM now only supports .txt files

***Update October 24, 2019***
- Fixed a bug in ATM

***Update October 26, 2019***
- file encrytion added to improve security

***Update October 29, 2019***
- Major changes added to bank.java to improve flow
- file encrytion has been implemented with new bank.java

***Update October 30, 2019***
- Major aesthetic changes

***Needs Work (ATM & BankTeller)***
- ability to cancel withdraw - Perhaps throw the cancel option in the main with the switch/case?
- BankTellerMainTemo is a tester for the BankTeller and will be incorporated into the proper main
- Need to include the balance in text files and BankTeller Methods
- ability to view stats

***Tasks***
- Security Questions
 - For the security questions, make an arraylist that the questions are added to, and make it keep reminding them.
- Making sure that it does not generate the same random number for the account file.
- Let user choose their own PIN.
- Change account number to 6 digits.
- Transfer balances from one account to another account.
- Make accounts that are limited and do not have as much capabilites as main accounts (i.e. student/children accounts). (Maybe)
