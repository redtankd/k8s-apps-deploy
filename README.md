# Deploying  Applications to Kubernetes

## PostgresSQL

## Zeppelin 

    The official zeppelin image doesn't support time zone. So a customized image need to be build. The Dockerfile is in zeppelin/timezone-image.

    The below configuartion depend on the envrionment:

    - config-map.yml: ZEPPELIN_K8S_CONTAINER_IMAGE is the zeppelin image. If the time zone is needed, replaced with customized image.
    - deployment.yml: The zeppelin image.
    - pv.yml: The hostpath.

    reference: 
    
    - https://zeppelin.apache.org/docs/0.10.0/quickstart/kubernetes.html
    - https://kubernetes.io/docs/tasks/manage-kubernetes-objects/kustomization/