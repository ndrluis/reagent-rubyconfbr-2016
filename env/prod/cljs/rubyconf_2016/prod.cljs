(ns rubyconf-2016.prod
  (:require [rubyconf-2016.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
