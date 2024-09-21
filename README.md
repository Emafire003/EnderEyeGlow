# EnderEyeGlow
Whenever you throw an Eye of Ender, it will get an outline like the Glowing effect, making it easier to track, in order to not loose it. 

It will also add particles when the item is dropped

If you install [ColoredGlowLib](https://modrinth.com/mods/coloredglowlib) too, the outline will also have a custom color!
[![bisecthosting](https://www.bisecthosting.com/partners/custom-banners/e9bbf36a-be01-4324-b393-dae88a01be66.webp)](https://www.bisecthosting.com/LightDev)

You can enabled or disable this features in the config file, which requires you to install ModMenu and YACL. You will also be able to customize the glow color.

## Vanilla alternative
You can also use a repeating command block always activated with the following command:

```
execute as @e[type=minecraft:eye_of_ender] run data merge entity @s {Glowing:1}
```

![EnderGlow-gif](https://github.com/user-attachments/assets/5613bf7f-cfe3-45ba-b39c-e1089e71522e)
