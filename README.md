# Banking kata in kotlin

## Purpose

The purpose of this project is to train different pattern like layered architecture, aggregate, command bus, event bus,... doing the Banking Kata (with adapted rules !).
Following a Domain Driven Design methodology and other good practices.

And to learn Kotlin too !!! 🥳

I will try to keep note of what I learned, each time I learn something.

## The subject

A banking system !

- Someone can create an account with a first positive deposit.
  - The deposit is an amount of money in a specified currency.
- An external currency system allow to know currencies exchange rates. 
- Someone can make a deposit of a positive amount of money.
- Someone can withdraw a positive amount of money.
  - It's not possible to withdraw more money than the current account balance.
- Someone can make a bank transfer to another account.
  - It's not possible to transfer more money than the current account balance.
- Each operation is sent to the account owner by mail and instant messaging.
