package com.config;


public enum Configuration {
    URL {
        private final String URLS_PATH = "D:\\output\\urls.txt";

        @Override
        public String getPath() {
            return URLS_PATH;
        }


    }, URN {
        protected final String URNS_PATH = "D:\\output\\urns.txt";

        @Override
        public String getPath() {
            return URNS_PATH;
        }

    };



    public abstract String getPath();


}