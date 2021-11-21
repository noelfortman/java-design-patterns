---
layout: pattern
title: Component Object Pattern
folder: component-object
permalink: /patterns/component-object/ 
categories: 
 - structural
 language: en
 tags:
 - testing
---

## Name / classification
Component Object Pattern

## Also known as

## Intent
Web development is shifting more and more towards reusable components. Frameworks like React, Polymer, Angular etc. provide various component friendly abstractions to make front-end code-bases more maintainable. So our web applications are now full of “widgets” that have same behavior. We can use component various times on single web page or re-use it on various web pages. Therefore it is logical to create abstraction which covers functionality of single component and reuse it across end-to-end tests.

## Explanation

## Class diagram

## Applicability
Use the Component Object pattern when
* You are writing automated tests for your web application and you want to separate the UI manipulation required for the tests from the actual test logic.
* Make your tests less brittle, and more readable and robust

## Tutorials

## Known uses

## Consequences

## Related patterns
* Page Object Pattern

## Credits
* [Component Object](https://lkrnac.net/blog/2016/10/component-object-pattern-example/)