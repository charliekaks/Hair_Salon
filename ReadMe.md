# HAIR SALON APP
### An app that allows the employer to add stylists and clients to her app. the app also assigns various clients to one stylist. the employer can update and delete the clients and stylist information whenever it is dimmed necessary.

## Created by :
*Charles C. Kakai*

## Technologies Used
`Gradle`
`postGres`
`Java`
`Spark`

## Prerequisites

### JRE and JDK

## Installation
`git clone `

## Running and Development
### Testing
`gradle test`
### Building
`gradle build`
### Running the App
`gradle run`

# SQL
*CREATE DATABASE hair_salon;*

*CREATE TABLE stylists ( id serial PRIMARY KEY, stylistname varchar, phonenumber int );*

CREATE TABLE clients ( id serial PRIMARY KEY, clientname varchar,phonenumber int, stylistid int );*