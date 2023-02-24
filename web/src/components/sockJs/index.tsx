'use client';
import React, {
  useEffect,
  FC,
  MutableRefObject,
  Dispatch,
  SetStateAction,
} from 'react';
import SockJS from 'sockjs-client';
import * as StompJS from '@stomp/stompjs';

interface SocketJsProps {
  client?: StompJS.Client;
  setClient: (client: StompJS.Client | undefined) => void;
}

const SocketJs: FC<SocketJsProps> = ({ client, setClient }) => {
  const connect = () => {
    const newClient = new StompJS.Client({
      // brokerURL: 'ws://10.111.3.121:8080/ws-miseat/ws',
      webSocketFactory: () => new SockJS(`${process.env.DOMAIN}`),
      connectHeaders: {
        login: 'user',
        password: 'password',
      },
      debug: function (err) {
        console.log(err);
      },
      reconnectDelay: 5000, //자동 재 연결
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: () => {
        subscribe();
      },
      onStompError: (frame) => {
        console.error(frame);
      },
    });

    newClient.activate();
    setClient(newClient);
  };

  const disconnect = () => {
    client?.deactivate();
    setClient(undefined);
  };

  const subscribe = () => {
    client?.subscribe(`/sub/3`, (msg: any) => {
      console.log('받은 메세지', msg.body);
    });
  };

  const publish = () => {
    if (!client?.connected) {
      return;
    }

    client.publish({
      destination: '/pub/health',
      body: JSON.stringify({ message: 'hi' }),
    });
  };

  useEffect(() => {
    connect();

    return () => disconnect();
  }, []);

  return (
    <>
      <div>
        <div id='menu'>
          <p>Welcome,</p>
        </div>
        <button onClick={() => publish()}>버어튼</button>
      </div>
    </>
  );
};

export default SocketJs;
