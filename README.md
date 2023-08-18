# Receipt Processor Application

A versatile solution for processing and managing receipts. Extract information from receipts, process data, and calculate reward points based on different rules and criteria.

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Setup & Installation](#setup--installation)
- [Usage](#usage)
- [Classes Description](#classes-description)

## Overview

The Receipt Processor Application extracts data like item description, purchase date, time, and total amount from receipts. Additionally, it calculates reward points based on predefined rules.

## Architecture

This application is built using Java, Spring Boot and is containerized for easy deployment and scalability.

## Setup & Installation

1. **Prerequisites**:
    - Docker installed on your machine.
  
2. **Installation**:
    ```bash
    git clone https://github.com/JayanthKarupothula/ReceiptProcessor.git
    cd ReceiptProcessor 
    docker build -t receiptprocessor:latest .
    ```

3. **Running the Application**:
    ```bash
    docker run -p 8080:8080 receiptprocessor:latest
    ```

Your application should now be running on `http://localhost:8080/`.

## Usage
Open PostMan and do the following operations
- **Adding a Receipt**: POST to `localhost:8080/receipts/process` with the required data.It gives an Id as response
- **Calculating  and Viewing points**: Use the GET endpoint `localhost:8080/receipts/{id}/points`.

## Classes Description

1. **Item**:
    Represents an individual item in the receipt. Contains details like description and price.

2. **Receipt**:
    Represents the entire receipt, including details of purchase such as retailer, date, time, and list of items.




