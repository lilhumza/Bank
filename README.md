***FIXED ALL ISSUES WITH TRANSACTIONHISTORY -
   REMOVED ABILITY TO DEPOSIT/WITHDRAW FROM BANKTELLER -
   PLEASE TEST IT OUT - 
   IF THERES ANY ISSUES CREATE A NEW ISSUE -
   ANY QUESTION? CREATE AN ISSUE***
   
# File Encryption
- created AES system to decrpt/encrpt files
- uses 32 bits
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



