# Robotica


## Para compilar todos las clases juntas.

Correr los comandos:

 `nxjc *.java`
 
  `nxjlink Outlet_Receive -o Outlet_Receive.nxj`
  
  `nxjlink Outlet_Connect -o Outlet_Connect.nxj`
  
  
  
  ## Cables conectados:
  
  ### Sensores

  #### Brick 1:
  
  - Sensor de luz costado izquierdo    ----> entrada 4
  - Sensor distancia costado derecho   ----> entrada 2
  - Sensor distancia costado izquierdo ----> entrada 1
  - Sensor color, costado derecho      ----> entrada 3
  
  #### Brick 2:
  
  - Optical distance sensor            ----> entrada 4
  - Sensor de luz derecho              ----> entrada 2
  - Sensor de luz izquierdo            ----> entrada 3
  - Sensor color, costado izquierdo    ----> entrada 1
  
  ### Motores
  
  #### Brick 2:
  
  - Motor costado izquierdo            ----> entrada C
  - Motor costado derecho              ----> entrada B
  - Motor para la polea                ----> entrada A
