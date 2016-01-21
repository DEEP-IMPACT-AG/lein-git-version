(ns lein-git-version.plugin
  (:require [leiningen.git-version :refer [get-git-version get-git-ref]]))

(defn middleware
  [project]
  (-> project
      (assoc :version (get-git-version))
      (assoc :gitref (get-git-ref))))
