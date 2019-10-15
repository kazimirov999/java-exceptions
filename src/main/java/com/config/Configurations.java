package com.config;


public enum Configurations {
    URL {
        @Override
        public Configuration getConfiguration() {
            return new UrlConfiguration("D:\\output\\urls.txt");
        }
    }, URN {
        @Override
        public Configuration getConfiguration() {
            return new UrnConfiguration("D:\\output\\urns.txt");
        }

    };

    public abstract Configuration getConfiguration();
}