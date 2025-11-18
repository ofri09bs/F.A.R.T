# F.A.R.T – Federal Agency of Ransom Technologies 💻⚔️

**Project Type:** Java Desktop Application (JavaFX)  
**Purpose:** Educational demonstration of file encryption/decryption with UI  

---

## Description

F.A.R.T is a federal agency that makes ransomware, and sell it to small hackers 👺. (not really)
- Files in the `test` folder are automatically encrypted when the application starts.  
- Encrypted files receive the `.enc` extension.  
- Files can be decrypted using a fixed password.  
- All encryption is done using a **single static key** for simplicity. 
- The UI features official-looking text with green color on a black background, a small log area, and text borders.

⚠️ **Warning:** This project is for educational purposes only. Do not run it on important files without backup.

---

## Requirements

- Java 17 or Java 25 (JDK)  
- JavaFX SDK 25 (or compatible version)  
- IDE: VSCode, IntelliJ, or any IDE that supports JavaFX  

---

## Limitations

- Uses a static key and fixed password (not suitable for real-world encryption).  
- Encryption algorithm is AES-GCM 128-bit.  
- No special handling for very large files – use caution with important data.
- Its just for fun , not really usable in real life

---

## Notes

- This project is intended for learning purposes only. Encrypting real files may result in permanent data loss if not backed up.  
- Possible future improvements:  
  - Dynamic password/key support    
  - Handling of large files
  - Make it more dangerous by letting it encrypt all the files on your computer and also make it an .exe file
  - Expand F.A.R.T to the world!
