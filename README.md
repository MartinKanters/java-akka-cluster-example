Akka Cluster Example in Java
=========================

## Introduction
This project was made while testing the Akka Cluster features.
It contains a small example of a router distributed in a cluster, written in Java.
Nodes can be added on the fly. 
You can specify on what port the node runs and if the node should send messages across the cluster.

## How to run
You can run the program like every ordinary Java main program. Make sure you have `mvn clean install`ed the project before to get the Akka dependency.

    ../Node 2550                for running on port 2550 (first seed-node)
    ../Node 2551                for running on port 2551 (second seed-node)
    ../Node                     for running on a random port
    ../Node 0                   for running on a random port
    ../Node 0 message-sender    for running on a random port and it will distribute message among the nodes

At least one of the seed-nodes needs to be up for the other nodes to join the cluster.

## Example
1. Run a node with `2550`  
2. Run a node with `0 message-sender`  
3. View results (messages should be handled 3 at a time in a round-robin fashion over the nodes)  
4. Add a node with `0` and view results.
