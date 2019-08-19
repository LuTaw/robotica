# Robotica


## Para compilar todos las clases juntas.

Correr los comandos:

 `nxjc *.java`

  `nxjlink Outlet_Receive -o Outlet_Receive.nxj`

  `nxjlink Outlet_Connect -o Outlet_Connect.nxj`



  ## Cables conectados:

  ### Sensores

  #### Brick 1:

  - Sensor Optical distance            ----> entrada 4  
  - Sensor distancia costado derecho   ----> entrada 2
  - Sensor distancia costado izquierdo ----> entrada 1
  - Sensor distancia adelante          ----> entrada 3

  #### Brick 2:
  -
  - Sensor de luz derecho              ----> entrada 4
  - Sensor color derecho               ----> entrada 2
  - Sensor de luz izquierdo            ----> entrada 3
  - Sensor de color izquierdo          ----> entrada 1

  ### Motores

  #### Brick 2:

  - Motor costado izquierdo            ----> entrada C
  - Motor costado derecho              ----> entrada B
  - Motor para la polea                ----> entrada A
