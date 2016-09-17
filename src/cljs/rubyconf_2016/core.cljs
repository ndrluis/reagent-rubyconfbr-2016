(ns rubyconf-2016.core
 (:require [reagent.core :as reagent :refer [atom]]
           [reagent.session :as session]
           [secretary.core :as secretary :include-macros true]
           [accountant.core :as accountant]))

(def click-count (atom 0))

(defn counter-component []
  [:div
    "Counter: " @click-count
    [:input {:type "button" :value "+"
             :on-click #(swap! click-count inc)}]
    [:input {:type "button" :value "-"
             :on-click #(swap! click-count dec)}]])

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Welcome to rubyconf-2016"]
   [:div [:a {:href "/counter"} "go to counter page"]]])

(defn counter-page []
  [:div [:h2 "Counter rubyconf-2016"]
   [counter-component]
   [:div [:a {:href "/"} "go to the home page"]]])

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes

(secretary/defroute "/" []
  (session/put! :current-page #'home-page))

(secretary/defroute "/counter" []
  (session/put! :current-page #'counter-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
