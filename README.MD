# SQL GPT

## Project Overview
This is a system built to test a Large Language Models ability to answer questions about a database by creating SQL to query the database.
The current state of the system uses ChatGPT but is built to be extendable for other LLMs.
The LLM is given the Database Schema, the question, and is prompted to return SQL to query the Database. The database is then queried and the returned data is given back to the LLM to answer the question.

## Examples
ResponseExamples.txt has example queries and answers to show how the LLM preforms.
