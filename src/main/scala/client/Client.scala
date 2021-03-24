package client

import automaticTester.TestAvatar
import machine.MachineImpl

object Client extends App {
  TestAvatar.check(MachineImpl)
}
