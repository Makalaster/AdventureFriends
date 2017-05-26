# AdventureFriends
A tabletop RPG for your Android devices

### Google play store link

https://play.google.com/store/apps/details?id=com.makalaster.adventurefriends

### Overview

This app is a platform to facilitate playing tabletop role-playing games, such as dungeons and dragons. The current version of the app includes an example game created for this project called "Goblins? Goblins!". Users are able to create and join game campaigns, create and manage sessions of each campaign, create and manage characters, and share a map, all through their Android devices. This eliminates the need for maintaining paper copies of books or character sheets, and helps users stay organized. It also helps reduce the need to be physically with one another in order to play a game, which was a common issue among those interviewed at the start of the project.

### Technologies

The core technology used in this app was Firebase. Firebase UI was used for authentication, and Firebase Database was used to store and sync data between users. Campaigns and user accounts are stored in the cloud, and are used to keep track of who is playing what game.

### Approach

The main approach to designing this app was trying to determine what information needed to be the most available based on user roles. There is a lot of information to keep track of in a tabletop RPG, and the traditional approach of character sheet and reference book often left much to be desired. More modern approaches, such as PDFs don't tend to improve much on the old systems.

The first task was to divide the experince by role, of which there are two 

### Known issues

- Viewpagers do not retain their tab on orientation change.
- If the app is closed and later reopened, it may crash when trying to resume.
- The map does not properly adjust to landscape orientation.
- Many game features were not implemented due to time constraints.

### Screenshots

<p align = "center">
  <img src="screenshots/sign_in.jpg" width="250"/>
  <img src="screenshots/campagins.jpg" width="250"/>
  <img src="screenshots/modules.jpg" width="250"/>
  <img src="screenshots/overview.jpg" width="250"/>
  <img src="screenshots/npcs.jpg" width="250"/>
  <img src="screenshots/new_npc.jpg" width="250"/>
  <img src="screenshots/new_note.jpg" width="250"/>
  <img src="screenshots/notes.jpg" width="250"/>
  <img src="screenshots/edit_note.jpg" width="250"/>
  <img src="screenshots/map.jpg" width="250"/>
  <img src="screenshots/create_join.jpg" width="250"/>
  <img src="screenshots/new_campaign.jpg" width="250"/>
  <img src="screenshots/new_character.jpg" width="250"/>
  <img src="screenshots/character_stats.jpg" width="250"/>
  <img src="screenshots/abilities.jpg" width="250"/>
  <img src="screenshots/equipment.jpg" width="250"/>
  <img src="screenshots/inventory.jpg" width="250"/>
</p>
