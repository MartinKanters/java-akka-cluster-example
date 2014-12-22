Akka Cluster Example in Java
=========================

## Introduction
This project was made while testing the Akka Cluster features.
It contains a small example of a router distributed in a cluster, written in Java.
Nodes can be added on the fly. 
You can specify on what port the node runs and if the node should send messages across the cluster.

## How to run
- ../Node 2550                for running on port 2550 (first seed-node)
- ../Node 2551                for running on port 2551 (second seed-node)
- ../Node                     for running on a random port
- ../Node 0                   for running on a random port
- ../Node 0 message-sender    for running on a random port and it will distribute message among the nodes

At least one of the seed-nodes needs to be up for the other nodes to join the cluster.
