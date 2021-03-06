# Tip Calculator 

## *Isaac Osafo Nkansah*

**Tippy** computes the tip and total amount for a bill. The app uses the base amount and tip percentage to calculate the amount owed, and it also describes the quality of service based on the tip.

Time spent: **5** hours spent in total

## Functionality 

The following **required** functionality is completed:

* User can enter in a bill amount (total amount to tip on)
* User can enter a tip percentage (what % the user wants to tip).
* The tip and total amount are updated immediately when any of the inputs changes.
* The user sees a label or color update based on the tip amount. 

The following **extensions** are implemented:

* Replaced the text describing the tip (“poor”, “good”, etc) with emojis.
* Centered text inside editables
* Added the ability to split the bill across any number of people.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/ike97/CS_194_Assignment_1/blob/master/demo.gif' title='Video Walkthrough' width='250' height='500' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.
The primary challenge here was figuring out how to set an initial number inside of an 
editable field which is set to take in only numbers. Eventually I realized you still use setText()
and pass in a string but the string should be able to properly parse to a number in order for 
it to be displayed

## License

    Copyright [2020] [Isaac Osado Nkansah]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.