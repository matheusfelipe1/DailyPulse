# DailyPulse
**Kotlin Multiplatform - Clean Architecture**

# Important
### To test this project, you must visit [News API](https://newsapi.org/) and create an account.

## Android
### Running the Project on Android
1. **Create the `strings.xml` File:**
   - In your Android project's `res/values/` directory, create a file named `strings.xml` if it doesn't already exist.

2. **Add `BASE_URL` and `API_KEY`:**
   - Insert the following lines into the `strings.xml` file, replacing `your_api_key` with your actual API key obtained from [News API](https://newsapi.org/):

    ```xml
    <resources>
      <string name="BASE_URL">newsapi.org</string>
      <string name="API_KEY">your_api_key</string>
    </resources>
    ```

## iOS
### Running the Project on iOS
1. **Add a New Scheme in Xcode:**
   - Open your project in Xcode.
   - Next to the **Run** button, click on the current scheme and select **"Edit Scheme..."**.

2. **Configure Environment Variables:**
   - In the **"Edit Scheme"** window, select the **"Run"** section.
   - Navigate to the **"Arguments"** tab.
   - Under **"Environment Variables"**, add the following variables:
     - **BASE_URL**: `newsapi.org`
     - **API_KEY**: `your_api_key` (replace with your actual API key from [News API](https://newsapi.org/))

3. **Run the Project:**
   - Click the **Run** button to start the project with the configured environment variables.
