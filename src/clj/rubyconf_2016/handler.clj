(ns rubyconf-2016.handler
 (:require [compojure.core :refer [GET defroutes]]
           [compojure.route :refer [not-found resources]]
           [hiccup.page :refer [include-js include-css html5]]
           [rubyconf-2016.middleware :refer [wrap-middleware]]
           [config.core :refer [env]]))

(def mount-target
  [:div#app
      [:h3 "ClojureScript has not been compiled!"]
      [:p "please run "
       [:b "lein figwheel"]
       " in order to start the compiler"]])

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css "/css/site.css")])

(defn loading-page []
  (html5
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "/js/app.js")]))

(defn cards-page []
  (html5
    (head)
    [:body
     mount-target
     (include-js "/js/app_devcards.js")]))

(defroutes routes
  (GET "/" [] (loading-page))
  (GET "/counter" [] (loading-page))
  (GET "/todo" [] (loading-page))
  (GET "/cards" [] (cards-page))
  (resources "/")
  (not-found "Not Found"))

(def app (wrap-middleware #'routes))
