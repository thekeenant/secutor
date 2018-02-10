package com.keenant.secutor.network.packet;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryonet.EndPoint;
import java.util.ArrayList;
import java.util.UUID;

public interface Packet {
  static void register(EndPoint endpoint) {
    Kryo kryo = endpoint.getKryo();
    kryo.register(EntityMovePacket.class);
    kryo.register(GladiatorPacket.class);
    kryo.register(JoinPacket.class);
    kryo.register(LeavePacket.class);
    kryo.register(LoginPacket.class);
    kryo.register(UpdatePositionPacket.class);
    kryo.register(WorldSetupPacket.class);

    // Miscellaneous objects...
    {
      kryo.register(ArrayList.class);
      kryo.register(Vector2.class);
      kryo.register(UUID.class, new Serializer<UUID>() {
        @Override
        public void write(Kryo kryo, Output output, UUID object) {
          output.writeString(object.toString());
        }

        @Override
        public UUID read(Kryo kryo, Input input, Class<UUID> type) {
          String uuidStr = input.readString();
          return UUID.fromString(uuidStr);
        }
      });
    }
  }
}
