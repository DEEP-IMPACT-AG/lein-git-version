(ns leiningen.git-version
  (:require [leiningen.help]
            [leiningen.jar]
            [leiningen.compile]
            [leiningen.core.main]
            [leiningen.core.project]
            [robert.hooke]
            [leiningen.test])
  (:use
   [clojure.java.shell :only [sh]]))

(defn get-git-version
  []
  (let [version (second (re-find #"release/(.*)"
                         (clojure.string/trim
                           (:out (sh
                                   "git" "describe" "--tags" "--match" "release/*")))))]
    (if (empty? version)
      "0.0.0"
      version)))

(defn get-git-ref
  []
  (apply str (rest (clojure.string/trim
                    (:out (sh
                           "git" "rev-parse" "--verify" "HEAD"))))))

(defn git-version
  "Show project version, as tagged in git."
  ^{:doc "Show git project version"}
  [project & args]
  (println (get-git-version)))
