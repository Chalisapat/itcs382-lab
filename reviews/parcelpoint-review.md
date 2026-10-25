| No. | Check | Code location | Finding | Severity | Suggested fix |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | Code quality | `ParcelService.java:76-78` | `FileWriter` is never closed or flushed, so `parcels.csv` is probably empty or incomplete. | Must fix | Use try-with-resources around the writer. |
| 2 | Code quality | `ParcelService.java:42` | `parcel.getCode() == code` compares Strings by reference, so pickup can fail for a valid code. | Must fix | Use `code.equals(parcel.getCode())`. |
| 3 | Code quality | `ParcelService.java:80-81` | The empty `catch (IOException e)` hides save failures. | Should fix | Report or rethrow the error. |
| 4 | Code quality | `ParcelService.java:6` | Unused import `java.util.Date`. | Note | Delete the import. |
| 5 | Code quality | `ParcelService.java:12` | Field `maxParcels` is never used. | Note | Delete it, or enforce it in `storeParcel`. |
| 6 | Code quality | `ParcelService.java:49` | `pickUp` returns 0 for an unknown code, which looks the same as a free pickup. | Should fix | Return a result that distinguishes “not found” (e.g. `OptionalDouble`, or throw). |
| 7 | Code quality | `Main.java:11-13, 17` | `words[1..3]` and `Integer.parseInt` are unchecked, so a short or non-numeric command crashes the program. | Should fix | Check `words.length` and catch `NumberFormatException`. |
| 8 | Code quality | `Main.java:8` | `Scanner` is never closed. | Note | Wrap it in try-with-resources. |
| 9 | Code quality | `Main.java:6-27` | `main` is a 22-line if/else chain that mixes parsing, dispatch and output. | Note | Extract one method per command. |
| 10 | Cohesion | `ParcelService.java:24-37` | `storeParcel` allocates a locker, changes the status, sends an SMS and saves a file. Cohesion is procedural. | Should fix | Move the SMS and save into separate methods or classes. |
| 11 | Cohesion | `ParcelService.java:39-50` | `pickUp` finds the parcel, frees the locker, saves and computes the fee. Cohesion is procedural. | Should fix | Split the lookup from the fee calculation. |
| 12 | Cohesion | `ParcelService.java:64-72` | `printParcels(boolean forStaff)` does one of two jobs depending on the flag. Cohesion is logical. | Should fix | Make two methods, `printForStaff()` and `printWaiting()`. |
| 13 | Cohesion | `Main.java:6-27` | `main` dispatches several unrelated commands. Cohesion is logical. | Note | One handler method per command. |
| 14 | Class cohesion | `ParcelService.java:9-83` | The class handles locker allocation, fee policy, SMS, CSV persistence and console printing. | Should fix | Extract at least a `FeePolicy` and a `ParcelRepository`. |
| 15 | Class cohesion | `ParcelService.java:52-62` | `fee` uses only its `parcel` argument and none of the class’s fields. | Note | Move it to `Parcel` or a fee class. |
| 16 | Non-private field | `Parcel.java:7`; written at `ParcelService.java:28, 44` | Public `status` is changed directly by another class. This is content coupling. | Must fix | Make it private and add a `markStored()` or `markPickedUp()` method. |
| 17 | Exposed collection | `ParcelService.java:20-21` | `getLockers()` returns the internal list, so callers can change it. | Should fix | Return an unmodifiable view, or add `freeLockerCount()`. |
| 18 | Coupling | `Main.java:19, 21 ↔ ParcelService.java:64` | The boolean flag controls the callee’s behaviour. This is control coupling. | Should fix | Replace it with two methods (see No. 12). |
| 19 | Coupling | `ParcelService.java:52` | `fee` receives a whole `Parcel` but needs only the size. This is stamp coupling. | Note | Pass the size, or move the method to `Parcel`. |
| 20 | Coupling | `Main.java:22` | `service.getLockers().stream()...` reaches through one object to use another. | Should fix | Add `service.freeLockerCount()`. |
| 21 | Coupling | `Main.java:13-14, 22` | `Main` depends on `ParcelService`, `Parcel` and `Locker`. | Note | Let the service accept raw values and hide `Parcel` and `Locker` from `Main`. |
| 22 | Connascence (convention) | `ParcelService.java:15-17, 56, 58`; `Parcel.java:6`; `Locker.java:5` | Size codes 1, 2 and 3 are magic numbers shared by four classes. | Should fix | Replace them with an enum `Size`. |
| 23 | Connascence (convention) | `ParcelService.java:28, 44, 68` | Status strings “STORED” and “PICKED_UP” must match across methods. | Should fix | Replace them with an enum `Status`. |
| 24 | Connascence (position) | `Main.java:11-13`; `Parcel.java:9` | `words[1..3]` and the `Parcel` constructor arguments depend on their order. | Note | Parse into named variables before constructing the `Parcel`. |
| 25 | Connascence (convention) | `ParcelService.java:53-61` | Free days (3) and rates (10.0, 15.0, 20.0) are hard-coded and tied to the size codes. | Should fix | Put them in named constants or the `Size` enum. |