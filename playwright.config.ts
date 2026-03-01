// playwright.config.ts
import { defineConfig } from '@playwright/test';

export default defineConfig({
  testDir: './tests',       // folder where your tests are located
  timeout: 30000,           // max time for each test
  retries: 0,               // retries on failure
  use: {
    headless: true,         // run tests in headless mode
    viewport: { width: 1280, height: 720 },
    ignoreHTTPSErrors: true
  },
});