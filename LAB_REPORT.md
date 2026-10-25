# Lab Report, Code Quality and Design Quality

Repository URL:

## Tools

| Item | Version or name |
| --- | --- |
| JDK |25.0.5 |
| Maven |3.10.0 |
| IDE | VS Code |
| SonarQube for IDE |SonarQube for IDE |
| AI tool and exact model |Claude 5.5 sonnet , Gemini |

## Task 1. SonarQube on the starter code

Commit of the starter, before any change:

Table 1. SonarQube issues in the starter code

| No. | Rule | File:line | Message |
| --- | --- | --- | --- |
| 1 | java:S106 | `src/parcelpoint/Main.java:9` | Replace this use of System.out by a logger. |
| 2 | java:S106 | `src/parcelpoint/Main.java:15` | Replace this use of System.out by a logger. |
| 3 | java:S106 | `src/parcelpoint/Main.java:17` | Replace this use of System.out by a logger. |
| 4 | java:S106 | `src/parcelpoint/Main.java:22` | Replace this use of System.out by a logger. |
| 5 | java:S6355 | `src/parcelpoint/ParcelService.java:1` | Use the "java.time" API for date and time. |
| 6 | java:S1128 | `src/parcelpoint/ParcelService.java:6` | Remove this unused import 'java.util.Date'. |
| 7 | java:S1068 | `src/parcelpoint/ParcelService.java:12` | Remove this unused "maxParcels" private field. |
| 8 | java:S106 | `src/parcelpoint/ParcelService.java:30` | Replace this use of System.out by a logger. |
| 9 | java:S1698 | `src/parcelpoint/ParcelService.java:42` | Strings and Boxed types should be compared using "equals()". |
| 10 | java:S106 | `src/parcelpoint/ParcelService.java:67` | Replace this use of System.out by a logger. |
| 11 | java:S106 | `src/parcelpoint/ParcelService.java:69` | Replace this use of System.out by a logger. |
| 12 | java:S2095 | `src/parcelpoint/ParcelService.java:76` | Use try-with-resources or close this "FileWriter" in a "finally" clause. |
| 13 | java:S108 | `src/parcelpoint/ParcelService.java:80` | Remove this block of code, fill it in, or add a comment explaining why it is empty. |

## Task 2. AI review of the starter code

| Item | Value |
| --- | --- |
| Date | 25 October 2026|
| AI tool and exact model | Claude 5.5 sonnet|
| Commit reviewed | 69ae323|
| How the AI read the files (attached the files, opened the folder, or pasted the files) | Attached files |
| Output file in the reviews folder | reviews/parcelpoint-review.md|

Table 2. All findings on the starter code

| No. | File:line | Finding | Found by (SonarQube, AI or both) | SonarQube rule or course term | Correct? (yes or no, and why) |
| --- | --- | --- | --- | --- | --- |
| 1 | `ParcelService.java:76-78` | FileWriter is never closed or flushed | Both | `java:S2095` | yes, because unclosed streams can leak system resources. |
| 2 | `ParcelService.java:42` | Comparing strings with `==` instead of `equals()` | Both | `java:S1698` | yes, because `==` compares object references, not string values. |
| 3 | `Parcel.java:7` | Public status field causes content coupling | AI | Content coupling | yes, because external classes can mutate internal state directly. |
| 4 | `Main.java:11-13` | Unchecked inputs and parsing can crash the app | AI | Robustness / Error handling | yes, because invalid user input causes NumberFormatException. |

2.1 Name two problems that only the AI review found.
  Public status field in Parcel.java causing content coupling , Unchecked array bounds and missing error handling for user inputs in Main.java.
2.2 Name one problem that only SonarQube found, or write none.
  Unused private field maxParcels in ParcelService.java.

2.3 Name one AI finding that was wrong or too vague, and explain why. If every finding was right, explain how you checked them.
  Every finding was correct and helpful for improving both code quality and design quality.

## Task 3. Fix and check

Table 3. What you did with each SonarQube issue in Table 1

| No. in Table 1 | Fixed or kept | Commit, or the reason for keeping it |
| --- | --- | --- |
| 1 | Fixed | 69ae323 |
| 2 | Fixed | 69ae323 |
| 3 | Fixed | 69ae323|
| 4 | Fixed | 69ae323 |
| 5 | Fixed | 69ae323 |
| 6 | Fixed | 69ae323 |
| 7 | Fixed | 69ae323 |
| 8 | Fixed | 69ae323 |
| 9 | Fixed | 69ae323|
| 10 | Fixed | 69ae323|
| 11 | Fixed | 69ae323 |
| 12 | Fixed | 69ae323 |
| 13 | Fixed | 69ae323 |

Number of SonarQube issues that remain after your fixes:
0
The two design problems that you fixed

| Design problem (course term) | What you changed | Commit |
| --- | --- | --- |
| Content Coupling | Encapsulated the `status` field in `Parcel.java` by making it `private` and adding getter/setter methods, then updated `ParcelService.java` to use `getStatus()` and `setStatus()`. | 69ae323 |
| Resource Leak / Exception Handling | Implemented try-with-resources for `FileWriter` in `saveToFile()` within `ParcelService.java` to ensure proper closure of system streams. |69ae323|

Output of the program after your fixes, for the commands `store P1 6501 2`, `pickup P1 5` and `staff`, and the content of parcels.csv:

```text
SMS to 6501: parcel P1 is in locker 2
P1 6501 PICKED_UP
Free lockers: 3
Content of parcels.csv:
P1,PICKED_UP
```

## Task 4. AI-generated poor and good designs

| Item | bikeshare-poor | bikeshare-good |
| --- | --- | --- |
| Date | 2026-10-25 | 2026-10-25 |
| AI tool and exact model | Gemini | Gemini |
| Prompt (B or C as given, or your changed prompt in full) | Prompt B (poor design) | Prompt C (good design) |
| Builds with mvn package (yes or no) | yes | yes |
| Number of classes, interfaces, enums and records | 1 class | 6 (1 class, 1 interface, 1 enum, 3 service/model classes) |
| SonarQube issues, total | - | - |
| SonarQube issues, by rule | - | - |
| AI review findings in Check 1 | - | - |
| AI review findings in Check 2 | - | - |
| Review output file in the reviews folder | - | - |

4.1 Did SonarQube report any cohesion or coupling problem in bikeshare-poor? Quote the rule and the message if it did.
  No, SonarQube was not run for this lab task, so no rules or messages were reported.
4.2 Did the AI review tell the two versions apart? Give one finding for bikeshare-poor that does not appear for bikeshare-good.
  Yes. For bikeshare-poor, the AI review highlighted that all public static fields and data management logic are tightly coupled and concentrated inside a single class (Main.java), which does not appear in bikeshare-good


4.3 Which version would you rather extend with a new member type, such as a visitor who pays 4 baht per minute, and why?

  bikeshare-good, because its use of the MemberType enum and separated service logic allows adding a new member type cleanly without modifying core business rules or monolithic functions.

## Task 5. Reflection

5.1 What does SonarQube check, and what does it not check? Answer with examples from this lab.
  SonarQube checks static code quality rules, code smells, bugs, and maintainability metrics.

5.2 When should you not trust the AI review? Give an example from this lab.
  When dealing with file/directory structures or environment-specific configurations , as the AI might misinterpret IDE visual representations.

5.3 What will you do differently in Project Phase 2 after this lab?
  We will design systems with high cohesion and low coupling from the beginning and properly configure Maven project structures.

## AI usage declaration

| AI tool and exact model | Purpose | Files or parts that it produced or changed | How you checked its output |
| --- | --- | --- | --- |
| Gemini | Generating Java code, Maven configuration, and fixing project structure | pom.xml, Main.java, MemberType.java, RideRepository.java, CsvRideRepository.java, Station.java, BikeShareService.java | Tested building both projects using mvn package|
