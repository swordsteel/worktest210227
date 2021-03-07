# Roulette

The aim of this exercise is to create a RESTful simple single player version of the
popular casino game **roulette** and then to propose some ideas about how we can
extend it to a multiplayer version.

## Part 1
Single player version of Roulette.

to run this api

    mvnw.cmd spring-boot:run

endpoint

    http://localhost:8080/api/bet

data to play 

    {
        "player_name" : "Straight/Single",
        "amount" : 0.1,
        "bet" : [ 0 ]
    }

or

    {
        "player_name" : "Straight/Single",
        "amount" : 0.1,
        "play" : odd
    }


bet typ
* single (any single number)
* split (any two adjoining numbers vertical or horizontal)
* street (any three numbers horizontal)
* trio (a three-number bet that involves at least one zero)
* corner (any four adjoining numbers in a block)
* six_line (any six numbers from two horizontal rows)
* odd
* even
* red
* black
* low
* high
* basket
* first_column
* second_column  
* third_column
* first_dozen
* second_dozen
* third_dozen
* snake

## Part 2
How to change to multiplayer player version of Roulette.
 
change endpoint to non-blocking.
change wheel service to backend service that run at specific times for spin events
change bet endpoint to listens for events.  
