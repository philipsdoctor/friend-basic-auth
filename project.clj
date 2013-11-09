(defproject friend-basic "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.reader "0.7.10"]
                 [clj-http "0.7.7"]
                 [hiccup "1.0.4"]
                 [ch.qos.logback/logback-classic "1.0.9"]
                 [hiccup "1.0.4"]
                 [ring "1.2.0"]
                 [ring-cors "0.1.0"]
                 [compojure "1.1.5"]
                 [crypto-password "0.1.1"]
                 [com.cemerick/friend "0.2.0"]]
  :ring {:handler friend-basic.core/app}
  :plugins [[lein-ring "0.8.6"]])

