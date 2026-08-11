# BiblioCraft Reshelved
 **BiblioCraft Reshelved** is an unofficial fork of [**BiblioCraft 1.12.2**](https://github.com/Nuchaz/BiblioCraft-Source) by Nuchaz. The focus of the fork is to fix issues with the mod and continuing its legacy on this version.
 
 #### **_IMPORTANT: This mod is going to stay on 1.12.2, there are no plans for porting from our side! You can find the modern port [here](https://www.curseforge.com/minecraft/mc-mods/bibliocraft-legacy)._**
 
 #### 🔧 **Notable Features**
 
 - Migrated to a modern gradle and cleaned up a lot of the code
 - Updated language translations
 - Recipes now properly use ore dictionary
 - Printing Press will now work with anything in the "dyeBlack" ore dictionary
 - Armor Stand slots now use the correct armor slot order and Forge item-slot checks
 - Armor Stand respects the Curse of Binding
 - Fancy Sign item rotations are consistent between the GUI and the world
 - Antique Atlas and Clipboard rendering works correctly with the Main Hand set to Left
 - Clipboard and Stockroom Catalogue support being used from the offhand
 - BiblioCraft inventory insertion and extraction now handle simulation, stacking, slot limits, and partial transfers correctly
 - Item drops preserve the complete ItemStack data, including Forge capabilities
 - Removed the client-side version check to prevent its world reference leak
 - BiblioCraft sounds are registered correctly for multiplayer
 - Fixed Recurrent Complex compat
 - Return of BiblioWoods [WIP]
