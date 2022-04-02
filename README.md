# Deploying  Applications to Kubernetes

## Kustomization

    ```
    kubectl apply -k ....
    kubectl delete -k ....
    ```

    reference: https://kubernetes.io/docs/tasks/manage-kubernetes-objects/kustomization/

## NGNINX ingress

    The official installation steps: https://docs.nginx.com/nginx-ingress-controller/installation/installation-with-manifests/

    The default ngninx-ingress-controller's service port is 80/443. With Docker Desktop, 80/443 is forbiden by OS. So 80/443 is unavailable for ingress.
    
    `kubectl port-forward ... 8080:80` works for normal needs. But the host' port that apps see is 80/443. If your app needs to know the host's original port 8080 that clients see, some complicated configuration in ngninx-ingress-controller is needed. But I don't know how to fix it. 
    
    So I change Ingress's port with annotation `nginx.org/listen-ports: "8080"`, then use `kubectl port-forward pod/nginx-ingress-... 8080:8080`. Now the host's port is not changed by the proxies.

    ```
    kubectl port-forward pod/nginx-ingress-.... 8080:8080 -n nginx-ingress
    ```

## PostgresSQL

    For Keycloak:

    ```
    creat user keycloak with password 'keycloak';
    create database keycloak;
    grant all on database keycloak to keycloak;
    ```

## Keycloak

    `keycloak.local` needs to be in `/etc/hosts`.

### keycloak/base
    
    A fast, dev-mode, no external storage demo.

    ```
    cd k8s-apps-deploy/keycloak
    kubectl apply -k base
    ```

    In browser: `http://keycloak.local:8080/`

### keycloak/with_postgres

    Integrated with PostgresSQL. 

    ```
    cd k8s-apps-deploy/keycloak
    kubectl apply -k with_postgres

    kubectl delete -k with_postgres
    ```
    
    TODO: password should goto secret !
    
## Zeppelin 

    `zeppelin.local` needs to be in `/etc/hosts`.

    reference: https://zeppelin.apache.org/docs/0.10.0/quickstart/kubernetes.html

### zeppelin/image

    The official zeppelin image doesn't support time zone. So a customized image need to be build. The Dockerfile is in `with_timezone`.

    ```
    cd zeppelin/image/with_timezone
    docker build -t zeppelin_with_tz:0.10.0 .
    ```

    A new image is build for pac4j intergration.

    ```
    cd zeppelin/image/with_pac4j_pg
    docker build -t zeppelin_with_pac4j:0.10.0 .
    ```

### zeppelin/base

    a fast, no external storage demo.

    ```
    cd k8s-apps-deploy/zeppelin
    kubectl apply -k base
    kubectl port-forward pod/nginx-ingress-... 8080:8080
    ```
### zeppelin/with_storage

    ```
    cd k8s-apps-deploy/zeppelin
    kubectl kustomize with_storage | sed 's#$(ZEPPELIN_NOTEBOOK_PATH)#/Users/redtank/var/lib/zeppelin/notebook#' | kubectl apply -f -

    kubectl kustomize with_storage | sed 's#$(ZEPPELIN_NOTEBOOK_PATH)#/Users/redtank/var/lib/zeppelin/notebook#' | kubectl delete -f -
    ```

### zeppelin/with_keycloak

    ```
    cd k8s-apps-deploy/zeppelin
    kubectl kustomize with_keycloak | sed 's#$(ZEPPELIN_NOTEBOOK_PATH)#/Users/redtank/var/lib/zeppelin/notebook#' | kubectl apply -f -

    kubectl kustomize with_keycloak | sed 's#$(ZEPPELIN_NOTEBOOK_PATH)#/Users/redtank/var/lib/zeppelin/notebook#' | kubectl delete -f -
    ```
